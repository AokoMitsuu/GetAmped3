	� G /item/bigbomb/mdl/bomb.mopo�       �V[o�0~N�=�X��(h�N(]WU�t����[�C� F�iRU��;�В���T���`�����=]H��9��k�D�:L2�2��,ɸ�h�%�
�$�E�5�&"�J����뿝|?�� s�r&�gЏ�@ԭ����vK�Šc�����"���V�C
;N����u~|u����х߾��
nEm����裓j���YC�n٩p�+�~�}�Y��W!���ᄄ|>�DR�݁Uբ	��)�x�Vf$��[$e	5l�=���1�s�䠪t� ��$��S.(�y64���JK��ŅBA~M��J�`�2�HI��2¶#�R�,Eݓ� C׌΁'&ib�X4����g�?�ʃ�㣌�� �-����$�)��������0��H1��]e���Ǳѽ���YxP�8ar��*��g%����E,o�br3�44׫�����O7|JS��Z�q^�Js��Ҫ���l,)^�Xy^$��W��|��+3�����A�]��"�c�nLBz�c.�^�8��Z�B�oLS�0�N���@���u��)�I]U�yF�T�c��d�@�իQ�����=��6}~�� to��u���n=���g:��V�=���,���5ݘ��*�P���Loα�F��|EY���*�S�ۣ��6{�˶�T�T<xS�V������ţ�_�X������!��~3��;~hc����Nݾ쥨{�!�'�(�[�Τ���d���F؁r�a��4�R��D3"B-@��yP���R0���Zq�X��ޓ�c�O�(�s�^E<\�*��u�b��`�����dCs�!�޿�\��ܟBE���s�ݚ2�4�k�_%�`�   \  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar1.agi���� red������� ore������� start 
showweapon startattack hit hitstage reflect broken setShadowState
 d u 3
 H 0 3 anime (FFFZ)V
 d x yA   A�  Bp   
setMixture (Lkotori/data/MixtureData;)V
 d ~  mixtureMotion (IFFF)V
 d � ��@   removeMixture
 d � 1 roll (Lkotori/geom/Vector3D;I)V
 f � �-Dz           rotate (Lkotori/geom/Quaternion;I)V
 f � ��� kotori.geom.Quaternion?�               	playSound
 d � 3-�          -�           
setVisible (Z)V
 f � � generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D /item/bigbomb/rays.scm�            ?�  ?�  ?�              ��?�              @@  A�   	fadeColor (FFFFI)V
 d � �>�����  =���@�  Ap  ��  D /item/bigbomb/shockwave.scm�            ?��?��?��    A�  Bp   D /item/bigbomb/shockwave.scm�            ?���?���?���              explode
 d � 1D /item/bigbomb/shockwave.scm�            ?�  ?�  ?�               D /item/bigbomb/shockwave.scm�            ?   ?   ?   A�  BH       D /item/bigbomb/shockwave.scm�    =���    ?L��?L��?L��             D /item/bigbomb/shockwave.scm�    =���    ?��?��?��             D /efx/explode/exp_cylinder1.scm�            ?333?333?333              attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            �� ga2.stage.atk.ExplodeAttack d2� � hit_bg   d � �-            �� d2/ �  d 
� � A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/flash/glow02_god.scm�    =L��    @�  @�  @�                red2D /efx/flash/glow02_god.scm�    =L��    @@  @@  @@               D /efx/flash/glow02_god.scm�    =L��    @@  @@  @@               D /efx/flash/glow02_god.scm�    =L��    @   @   @                D /efx/flash/glow02_god.scm�    =L��    @   @   @                 kill
 f � 1 f h g j g l g - 1  i� k�m��  n 1  *� vW�  o 1  1*� wW*{|}� zW*� � �W*�� �W* ȶ wW*� �W�  p 1  *� wW*�� �W�  q 1  *� wW*�d� �W�  r 1  #*� wW*{� �W*�� �W*,� wW*� W�  s 1  *� wW*{� �W*�� �W�  t 1 z*� �W*� wW*�� �W*�d� �W*��� zW*��d� �W*�� �W*�� �W*0� �W* ȶ wW*d� �W*� �W*��d� �W*�� �W*�� �W* ȶ wW*d� �W*� �W*�� ȶ �W*���� zW*�� �W*�� �W*�� �W*�� �W*� wW*� �W*�� �W*� wW*�� �W*�� �W*� wW*�� �W*� �W*�� �W*� �W*��� �W*��� �W*!� �W*��� �W*��� �W*��� �W*��� �W*��� �W*� �W*�� wW*� �W�     =Өd�  >���>Ǯ~>���  