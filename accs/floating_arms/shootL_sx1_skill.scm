	� G %/accs/floating_arms/floating_arms.mot +�       �W_o�F&Rԯ�"/���?%"<$��HRz꛵��l������)/}�c�J�����}�ή�`Dۻ��H��vf����<��-YK��
�g	�Cb��K2�8]������K�IƩ5�[϶�C�b��^��!Pƥ�xwxP�ӄ��y]�$!��,F�k���5��k��$]"�&��y�����$�cIt4�u�6�K�i�HKq��-�0�i���Xn��D�#K'@/�%�s������ID?�]ߎ`A��o� �J��pL���@��0Q��A �yİ�I�c[,�#_�]_��Q��v�Ds	b"v����TD����ٙ���������Ͽ����?,���8:GjÎ�K��O�s}���c�s�'�/��_G����j��Do�E|C1.F������K1n����Ż��7wRՓ�/fp�:0��������DT�s�H{�iѳf����Q��%te�L�B���'���t�g]���w:ʨ>�r���A=t}���������zp9Q[k��{i��!{��g��5o�2�<�k�E�p-��.�J���5�Rbڣ8���)/�on�T	��Nc�X�����8�!O��Zի�-]���
�����z~#P巁�m���@������N����ra���q�4��j�J Vb���
܄��jDՈQS��X�]�
x��t��y���#��j��M��.��h����ۣ�8�7�����˩�v�����7��=ᙻ�H��q�V�c(3[6ڮ��$2�D�an,ǩ����)�v�O�B(����rz����B�Q�3>cv����m���G����B���R�|N�@�$R�*�'a�GbA�t���? ����.�>(Y�ZH��Ӂ�/��y�i`kn��������I�"Eû'<h��p�����^q����s�ѫ8�1���9]�eNZ��|����'��P�{�K�$�2���l�p�����-v�њI<X&ZHp3�5��t����1Ua�����5�o�|����y����]�TD�}?��[�/* z�|   |  Lga2/stage/AmpedScript; e start start2 hitstage timeout efx mix beam beam2 beam_end efx_end finishAllConnections
 d q 1 removeMixture
 d s 1 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d u vD /efx/afterimage/afterimage.scm=L��    =���>���>���>���             start_elec_gre6 setItem C(IILkotori/geom/Vector3D;Ljava/lang/String;ZILjava/lang/String;II)V
 f z {-             ,/accs/floating_arms/shootL_sx1_skill_obj.scm floating_arms_L_skill
 H 0 3 kill
 f � 1-             setRenderType
 d � 3 setBlend
 d � 3 setColor (FFFF)V
 d � � setShadowState
 d � 3 setEdge (Z)V
 f � � anime (F)V
 d � �D~   
setVisible (ZI)V
 d � � 
setMixture (Lkotori/data/MixtureData;)V
 d � ��� kotori.data.MixtureData� /efx/mix/bar2.agif�L mixtureMotion (IFFF)V
 d � �A    (FF)V
 d � �Ds3=���D� >��D�  =L��D�@ >L�����f�L Lkotori/geom/KMath; � D2R (F)F
 � � �B�  D /efx/gun/flush_doublegun.scm            >���>���?��             	start_greD /efx/flash/glow.scm        >L��?���?���?���             	lightgre2D /efx/aura/aura03.scm        ?   ?�  ?�  ?��C4           
start_gre4D /efx/ripple/ripple01.scm    =L��    @�  @�  @�               moment clearGenerate
 d � 1 /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d u � setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/laser/l_bbss_gre2.scm            ?   ?   ?               D /efx/aura/aura03.scm        ?   ?�  ?�  ?��C4          ���f�LD /efx/gun/flush_doublegun.scm        >���?L��?L��?��            D /efx/flash/glow.scm        >L��?���?���?���            D /efx/aura/aura03.scm        ?   ?�  ?�  ?��C4          D /efx/ripple/ripple01.scm    =L��    @�  @�  @�              D /efx/laser/l_bbss_gre2.scm        >���?�  ?�  ?�              D /efx/aura/aura03.scm        ?   ?�  ?�  ?��C4          D /efx/electric/lightning08.scm            ?�  ?�  ?�               Lkotori/scp/KotoriScript; � 
accumlimit
 � � 3 rndc
 H � � Lkotori/data/KGLGenerationData; � pos (FFF)V
 � � �?333 rndf
 H � � hpb
 � � �C�   scale
 � � �?fff 
start_gre3D /efx/afterimage/afterimage.scm        �L��@   @   @               
 f � � f 
 g 1  *� rW*� tW*� W�  h 1  *� rW*� tW*� W*xy� wW�  i 1   *}~u0� |W*d� �W*� �W�  j 1   *�~u0� |W*d� �W*� �W�  k 1  A*� �W*� �W*� �W*� �W*� �W*�� �W<� *� �W�W$���  l 1  #*� rW*�� �W*�� �W*d� �W*� tW�  m 1  �*��� �W*d� �W*��� �W* �� �W*��� �W*2� �W*��� �W*2� �W*� rW*�� �W*\\� �W*�� �� �W*��� wW*��� wW*��� wW*��� wW*� �W**�� �� �W*2� �W*��� wW�  n 1 *��� �W*d� �W*��� �W* �� �W*��� �W*2� �W*��� �W*2� �W*� rW*�� �W*\\� �W*�� �� �W*��� wW*��� wW*��� wW*��� wW*ƶ �W*2� �W*��� wW*d� �W�L=� f>� N* ȶ �W+��j�� �b�b� �W+ٸ �ٸ �ٸ ֶ �W+��� �b� �W*+ݶ wW�W���*h� �W�W����  o 1  *� rW*� tW�  p 1  )*�y� wW*d� �W*� �W*� rW*� �W*� �W� *���    ?S(6���p?�L^?>t�?�[�  