	� G 5/efx/etc/efx_magic_houki/throw_objs/poison_apple2.mot&�       �U�N�@�UJ���
.�b�����F�B	E��M�IV�^��@*ġ��^z��j���0ii���v�D����xfwv�o~r�����k�������ou�R��Y�D�#.x�L�b��f�=�C�����P�6��h�����J�r���~�z��^�pg�iss�6,�Zp�j�d�$=� M� �C����6�<���� �1m�+Mv��p*�"h1��0/N4��1F1��m=ߢM� �B�!?�5yl����b�RY��L;�B��<ŶO�Ci���
�E�#]��C:0���]|���~sy���R>��G��Dh�R*Z}�󓖀��+��ʦߣX$*�U���D�ǻ�#�D~�V��q��E�+Y�Go?�����ݗ����O�>�W��"/W���>�ˮ��J����c������ħ�<�	P3C�pDYǴ)�L�n,�U.��q;'�=��'Y�zu�s�op�h�w�[�@ k�{�²���R���4���I����.Ԏs�Π�u���''�ĲlO3�z+�XHq�plí���Z�ip��ʬ#1t�R��{�˺ǻ;-����;���݀�}����������,3�~���Y�p��r�c��'Ugc�}&f���l2����qm��WD@޴� �&SnB-X��֐��ם�t��Ǹ���8���sX�`$�	��z+e�d-g,�s��?�B�J�EM��cf3ndsG=K��w�gOh7�`�y���WHr!�nM&�0�V��P�s�_�t5��   S  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar4.agi��� Lkotori/geom/Quaternion; rot�� kotori.geom.Quaternion?�               i Lkotori/data/KGLGenerationData; p_smoke3D /efx/poison/p_smoke3.scm�            >���>���>���              start 
showweapon hit timeout hitstage reflect setShadowState
 d w 3 setEdge (Z)V
 f y z (FFF)Lkotori/geom/Vector3D;
 H  | roll (Lkotori/geom/Vector3D;I)V
 f ~ �H  
 H 0 3 	fadeColor (FFFFI)V
 d � �>��� n pos (FFF)V
 � � �=L���L�� hpb
 � � � generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �=��� rndf (F)F
 H � �C�   anime (FFFZ)V
 d � �@�  A   A�   setColor (FFFF)V
 d � � 
setMixture (Lkotori/data/MixtureData;)V
 d � � mixtureMotion (IFFF)V
 d � �D /efx/flash/flash_arbs.ptc�����=���    ?�  ?�  ?�                removeMixture
 d � 1 vibe
 d � �<��
B�   	playSound (IIF)V
 d � � 
setVisible
 f � zD /efx/poison/p_smoke8.ptc�            ?��?��?��              kill
 f � 1
 d � 3�   f h g k j m  o n - 1  i� l�p��  q 1  �*� xW*� {W*�� }� �W*2� �W*�� ȶ �W����� �W�� �W*�� �W*d� �W���� �W�� �W*�� �W�� 6*d� �W��� ��� ��� �� �W*�� �W* ȶ �W�Y`�W���ɱ  r 1  H*� �W*���� �W*� �W*� � �W*�� �W*d� �W*�� �W* �� �W*� �W�  s 1  s*� �W*����,� �W*b� �W*� }� �W*
� �W*� �W*�� �W*�� �W*����,� �W*
� �W*� �W*,� �W*� �W�  t 1  *� W�  u 1  *� W�  v 1  A�� �W�� �W*�� �W*� �W*{� �W*�� }� �W*�� �W*� �W� ���    =`��    >0>0�D>0�  