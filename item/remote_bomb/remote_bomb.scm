	� G  /item/remote_bomb/remotebomb.mot=�       �V_oE��|���rn�Ή���C���4U*ެ=ߜ����iw�:���J��$B ��gx*�/Ӻ������8AUB��vggg~3;�[W����,�dH�M���[i��@�\uW�+U��\���!U��S��`͈*�D<�'F:��Δ,/|�w�� l��>E[D@Dzd�$��+Պ�I�ւx�J٤R4��p%�����/O�8��n���9�G(��w�^1�.[8Pgj �T[����_�{�������f�ݟo��6��՛�?�T�8���`A�\�L%�W3R���4�յg�JPG�x'�9xs����g'����g�բK��$���OL	�@N���w��=/h��Y�N�&?�DC� ��B���4���ݞ1o����I(�7V�1Y����Fd������5�ؽq4L!S[y.8�拏)�$��j���Q���ԤϠ0�UOo����q6�So���1ͳ������ٱ�������BZ��vǟ�ul���c�7�U��ս����c,?� m	H��~�Ӱ<���.!9� M88��`�p8��K16F���ӆ����x�w]�c��{��16g�`�u�s����bp	&L��6`L�l�> ���%��A�na�~U��8�P��m���g�d�З)���۱1��8nۇM{Y����l�� pG�.�Z,�ڤ��l�Wb�19�J$"g�����ѧ�M�	��z�c��qyA{�jo^oaciĔ�A��)�X����{���ĝP���2�1!^XBF����Q����g?;���_���5�mG)W�O���%f�hl6�j��ʊHd7���1�`�'H���y��:���I�M'L��+�U����������Jm���cCr;����TY�u3�%� �'h���Y�S*�N��n-�5����N��pl٥��a|]���X�u�ou��^��p��FB��qf\ Q9��x�Ff�7K�ֵ~�e>�˴��)$Sp�R������ί̞�fӅ��(���I"U͋��a�^�2���ǖ+�0M�3��৐�����}��
   P  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; red�� kotori.data.MixtureData� /efx/mix/bar1.agi���� start startattack wait reflect hitstage exp timeout broken setRenderType
 d r 3  /
 H 0 3 size (F)V
 f v w=��� setJudgmentApproach
 f z w roll (Lkotori/geom/Vector3D;I)V
 f | }-�z           anime (FFFZ)V
 d � �@�  A�  A   �    generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � �D $/item/remote_bomb/remote_bomb_hl.scm�            ?�  ?�  ?�              D !/efx/ripple/ripple_pulsegun_1.scm�    ?333    ?333?333?333             blue2D !/efx/ripple/ripple_pulsegun_1.scm�    ?333    ?��?��?��            D !/efx/ripple/ripple_pulsegun_1.scm�    ?333    ?   ?   ?                getPressedButton
 f � 2 finishAllKGL
 f � 1 	playSound
 d � 3-�          -�           rotate (Lkotori/geom/Quaternion;I)V
 f � ��� kotori.geom.Quaternion?�               	fadeColor (FFFFI)V
 d � � removeMixture
 d � 1 
setMixture (Lkotori/data/MixtureData;)V
 d � � mixtureMotion (IFFF)V
 d � ���   attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            �� ga2.stage.atk.ExplodeAttack�2� � hit_bc   d x � /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/explode/exp_sphere1.scm�            ?   ?   ?                 
setVisible (Z)V
 f � � kill
 f � 1 f h g	 - 1  i� �  j 1  I*t� sW*d� uW*� xWD=� ##fyjbD`=*� uW���*� xW*� {W*� W�  k 1  *� uW*� ~W�  l 1  �*��� �W* �� uW*���� �W*�j� �W*��� �W*2� uW*��� �W*2� uW*��� �W*^� uW*� �<� *� uW*� �<� *� �W*� W���  m 1  *� uW*{� �W*�� ~W�  n 1  ,*� uW*{� �W*�� ~W*,� uW*�d� �W*� W�  o 1  ^*� uW*d� �W*� �W*yy ȶ �W*� � �W*�� �W*2� uW*��� �W*�� �W*� �W*� �W*� �W�  p 1  *� �W*� W�  q 1  *� �W*� W�         ��  >���>�~'>����  